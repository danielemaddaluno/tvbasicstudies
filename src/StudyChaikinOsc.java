/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "ChaikinOscillator", title = "Chaikin Oscillator", shortTitle = "Chaikin Osc")
@StudyBand(title = "Zero", value = 0, color = "#606060")
public class StudyChaikinOsc extends Study
{
    @StudyArgSourceLength(name = "fast length", defval=3)
    int shortLength;

    @StudyArgSourceLength(name = "slow length", defval=10)
    int longLength;


    @StudyPlot(name = "ChaikinOscillator")
    @StudyPlotStyle(title = "Chaikin Oscillator", color = "#ff8080")
    Series out;

    @Override
    public void init()
    {
        final Series ad = accdist();
        out = diff(
                ema(ad, shortLength),
                ema(ad, longLength));
    }
}
