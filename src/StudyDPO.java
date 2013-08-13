/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "DetrendedPriceOscillator", title = "Detrended Price Oscillator", shortTitle = "DPO")
public class StudyDPO extends Study
{
    @StudyArgSourceLength(name = "period", defval=21)
    int period;

    @StudyPlot(name = "DPO")
    @StudyPlotStyle(title = "Detrended Price Oscillator", color = "#c0c000")
    Series out;

    @StudyPlotOffset(plot = "DPO")
    int offset;

    @StudyArgBool(name = "centered", title = "Centered", defval = false)
    boolean isCentered;

    @Override
    public void init()
    {
        final Series close = close();

        if (isCentered)
        {
            out = diff(offset(close, (period / 2 + 1)), sma(close, period));
            offset = -(period / 2 + 1);
        }
        else
        {
            out = diff(close, offset(sma(close, period), (period / 2 + 1)));
        }
    }
}
