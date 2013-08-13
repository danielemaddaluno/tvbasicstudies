/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "PriceOsc", title = "Price Oscillator", shortTitle = "PPO")
public class StudyPriceOsc extends Study
{
    @StudyArgSourceLength(name = "short length", defval=10)
    int shortLength;

    @StudyArgSourceLength(name = "long length", defval=21)
    int longLength;

    @StudyArgSource(name = "source", defval= BarSet.Source.CLOSE)
    Series source;

    @StudyArgBool(name="exponential", defval = false)
    boolean exponential;

    @StudyPlot(name = "PriceOsc")
    @StudyPlotStyle(title = "OSC", color = "#159980")
    Series out;

    @Override
    public void init()
    {
        final Series shortMA = esma(source, shortLength, exponential);
        final Series longMA = esma(source, longLength, exponential);
        out = percent(diff(shortMA, longMA), shortMA);
    }
}
