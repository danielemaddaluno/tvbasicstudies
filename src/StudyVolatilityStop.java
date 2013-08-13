/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;
import studylib.sdk.Series;
import studylib.sdk.studies.BarState;
import studylib.sdk.studies.SeriesExpr;
import java.util.List;


@StudyDecl(studyId = "VSTOP", isPriceStudy = true, title = "Volatility Stop",
           shortTitle = "VStop")
@StudyPalette(name = "volStopPalette",
              colors = {@StudyColor(color = "#007f0e", title = "Up"),
                        @StudyColor(color = "#872323", title = "Down")})
public class StudyVolatilityStop extends Study
{
    private final static int vstopOutputIndex = 0;
    private final static int colorOutputIndex = 1;

    @StudyArgSourceLength(name="length", defval=20)
    int length;

    @StudyArgDouble(name="multiplier", defval=2.0)
    double multiplier;

    @StudyPlot(name = "VolatilityStop", palettePlotName = "VolatilityStopColor", paletteName = "volStopPalette")
    @StudyPlotStyle(title="", color="#3a6ca8", plotStyle= CanvasEx.PlotStyle.CROSS)
    Series outVstop;


    @Override
    public void init()
    {
        final Series atr = atr(length);

        List<Series> out = newSeries(new SeriesExpr(barSet())
        {
            final Series closeInput = using(close());
            final Series atrInput = using(atr);

            LocalVar<Double> vstopPrev = newDouble(0.0);
            LocalVar<Double> maxClose = newDouble(0.0);
            LocalVar<Double> minClose = newDouble(0.0);
            LocalVar<Boolean> isUpTrend = newBoolean(true);
            LocalVar<Boolean> isTrendChanged = newBoolean(false);

            final SeriesOut vstopOutput = newOutput();
            final SeriesOut colorOutput = newOutput();

            @Override
            public void update(int i, BarState state)
            {
                vstopOutput.set(i, calcStudyAt(i));
                colorOutput.set(i, !isUpTrend.get() ? 1 : 0);
            }

            private double calcStudyAt(int i)
            {
                double vstop = calcVstopValue(i);

                calcTrendDirection(i, vstop);

                vstop = updateVstopValueAccordingToTrendDir(i, vstop);

                vstopPrev.set(!Double.isNaN(vstop) ? vstop : 0.0);

                return vstop;
            }


            private double calcVstopValue(int i)
            {
                double vstop;

                maxClose.set(Math.max(maxClose.get(), closeInput.at(i)));
                minClose.set(Math.min(minClose.get(), closeInput.at(i)));

                if (isUpTrend.get())
                {
                    double stop = maxClose.get() - multiplier * atrInput.at(i);
                    vstop = Math.max(vstopPrev.get(), stop);
                }
                else
                {
                    double stop = minClose.get() + multiplier * atrInput.at(i);
                    vstop = Math.min(vstopPrev.get(), stop);
                }
                return vstop;
            }

            private void calcTrendDirection(int i, double vstop)
            {
                if (closeInput.at(i) - vstop >= 0.0)
                {
                    if (!isUpTrend.get())
                    {
                        isUpTrend.set(true);
                        isTrendChanged.set(true);
                    }
                }
                else
                {
                    if (isUpTrend.get())
                    {
                        isUpTrend.set(false);
                        isTrendChanged.set(true);
                    }
                }
            }

            private double updateVstopValueAccordingToTrendDir(int i, double vstop)
            {
                if (isTrendChanged.get())
                {
                    maxClose.set(closeInput.at(i));
                    minClose.set(closeInput.at(i));
                    isTrendChanged.set(false);

                    if (isUpTrend.get())
                    {
                        vstop = maxClose.get() - multiplier * atrInput.at(i);
                    }
                    else
                    {
                        vstop = minClose.get() + multiplier * atrInput.at(i);
                    }
                }

                return vstop;
            }
        });

        outVstop = out.get(vstopOutputIndex);
        setPalette(outVstop, out.get(colorOutputIndex));
    }
}
