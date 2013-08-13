/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "SMIErgodicIndicator", title = "SMI Ergodic Indicator", shortTitle = "SMII")
public class StudySMIErgodic extends Study
{
    @StudyArgSourceLength(name = "long period", defval=20)
    int longLength;

    @StudyArgSourceLength(name = "short period", defval=5)
    int shortLength;

    @StudyArgSourceLength(name = "signal line period", defval=5)
    int signalLength;


    @StudyPlot(name = "SMI")
    @StudyPlotStyle(title = "SMI", color = "#0094ff")
    Series ergodic;

    @StudyPlot(name = "Signal")
    @StudyPlotStyle(title = "Signal", color = "#ff6a00")
    Series signal;


    @Override
    public void init()
    {
        ergodic = tsi(close(), shortLength, longLength);
        signal = ema(ergodic, signalLength);
    }
}