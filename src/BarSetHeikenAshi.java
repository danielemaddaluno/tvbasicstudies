/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;
import studylib.sdk.studies.BarSetExpr;
import studylib.sdk.studies.BarState;

@StudyDecl(studyId = "BarSetHeikenAshi", isHiddenStudy = true)
public class BarSetHeikenAshi extends OHLCVStudy
{
    @Override
    public void init()
    {
        final BarSet barSet = heikenAshi(barSet());
        open = barSet.open();
        high = barSet.high();
        low = barSet.low();
        close = barSet.close();
        volume = barSet.volume();
    }

    private BarSet heikenAshi(final BarSet sourceBarSet)
    {
        final DynBarSet haBarSet = newDynBarSet(sourceBarSet.src().resolution());
        return newBarSet(new BarSetExpr(haBarSet)
        {
            final TimeScale sourceTime = using(sourceBarSet.timescale(), 2);
            final Series sourceOpen = using(sourceBarSet.open(), 2);
            final Series sourceHigh = using(sourceBarSet.high(), 2);
            final Series sourceLow = using(sourceBarSet.low(), 2);
            final Series sourceClose = using(sourceBarSet.close(), 2);
            final Series sourceVolume = using(sourceBarSet.volume(), 2);
//            Double prevHAOpen = Double.NaN;
//            Double prevHAClose = Double.NaN;
            LocalVar<Double> prevHAOpen = newDouble(Double.NaN);
            LocalVar<Double> prevHAClose = newDouble(Double.NaN);

            @Override
            public void update(int i, BarState state)
            {
                final long time = sourceTime.timeAt(i);
                final double open = sourceOpen.at(i);
                final double high = sourceHigh.at(i);
                final double low = sourceLow.at(i);
                final double close = sourceClose.at(i);
                final double volume = sourceVolume.at(i);

                if (state.isFirstBar())
                {
                    prevHAClose.set(close);
                    prevHAOpen.set(open);
                }

                final double haClose = (open + high + low + close) / 4.0;
                final double haOpen = (prevHAOpen.get() + prevHAClose.get()) / 2.0;
                final double haHigh = Math.max(high, Math.max(haOpen, haClose));
                final double haLow = Math.min(low, Math.min(haOpen, haClose));

                if (state.isNewBar())
                {
                    newBar(time, haOpen, haHigh, haLow, haClose, volume);
                }
                else if (state.isRtBar())
                {
                    updateBar(haOpen, haHigh, haLow, haClose, volume);
                }

                if (state.isConfirmedBar())
                {
                    confirmBar();
                }

                prevHAOpen.set(haOpen);
                prevHAClose.set(haClose);
            }
        });
    }
}
