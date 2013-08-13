/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;
import studylib.sdk.studies.BarState;
import studylib.sdk.studies.SeriesExpr;

@StudyDecl(studyId = "VWAP", isPriceStudy = true,
        title = "VWAP", shortTitle = "VWAP",
        supportedResolutions = {Resolution.Type.intraday})
@StudyOffset(min=-500, val=0, max=500)
public class StudyVWAP extends Study
{
    @StudyPlot(name = "VWAP")
    @StudyPlotStyle(title = "VWAP", color = "#3A6CA8")
    Series out;

    @Override
    public void init()
    {
        out = newSeries(new SeriesExpr(barSet())
        {
            final Series src = using(hlc3(), 2);
            final Series vol = using(volume(), 2);
            final TimeScale time = using(timescale(), 1);
            final SeriesOut out = newOutput();

            // TODO: extract PeriodBuilder as separate class and use it in BarBuilderBarSet.java
            final BarBuilder builder = barBuilder(resolution());
            private int barIndex = BarBuilder.PRE_SESSION;

            private double sumSrc = 0;
            private double sumVol = 0;

            @Override
            public void update(int i, BarState state)
            {
                final long time = this.time.timeAt(i);
                int index = builder.indexOfBar(time);

                if (index == BarBuilder.POST_SESSION)
                {
                    // switch to new session
                    builder.moveTo(time);
                    barIndex = BarBuilder.PRE_SESSION;

                    index = builder.indexOfBar(time);

                    periodReset(i);
                }

                if (index < 0)
                {
                    return;
                }

                if (index == barIndex)
                {
                    // same session, same bar
                    periodUpdate(i);
                }
                else
                {
                    // same session, new bar
                    barIndex = index;

                    periodNew(i);
                }
            }

            void periodReset(int i)
            {
                sumSrc = 0;
                sumVol = 0;
            }

            void periodNew(int i)
            {
                if (i > 0 && barIndex > 0)
                {
                    final double src = this.src.at(i-1);
                    final double vol = this.vol.at(i-1);

                    if (!Double.isNaN(src) && !Double.isNaN(vol))
                    {
                        sumSrc += src * vol;
                        sumVol += vol;
                    }
                }

                periodUpdate(i);
            }

            void periodUpdate(int i)
            {
                final double src = this.src.at(i);
                final double vol = this.vol.at(i);
                final boolean isOk = !Double.isNaN(src) && !Double.isNaN(vol);
                final double totSrc = isOk ? sumSrc + src * vol : sumSrc;
                final double totVol = isOk ? sumVol + vol : sumVol;
                out.set(i, totSrc / totVol);
            }
        }).get(0);
    }
}
