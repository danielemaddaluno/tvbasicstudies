/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "SMIErgodicOscillator", title = "SMI Ergodic Oscillator", shortTitle = "SMIO")
public class StudySMIErgodicOsc extends Study
{
    @StudyArgSourceLength(name = "long period", defval=20)
    int longLength;

    @StudyArgSourceLength(name = "short period", defval=5)
    int shortLength;

    @StudyArgSourceLength(name = "signal line period", defval=5)
    int signalLength;


    @StudyPlot(name = "SMIOSC")
    @StudyPlotStyle(title = "SMI Ergodic Oscillator", color = "#ff8080", plotStyle = CanvasEx.PlotStyle.HISTOGRAM)
    Series out;


    @Override
    public void init()
    {
        final Series ergodic = tsi(close(), shortLength, longLength);
        final Series signal = ema(ergodic, signalLength);
        out = diff(ergodic, signal);
    }
}
