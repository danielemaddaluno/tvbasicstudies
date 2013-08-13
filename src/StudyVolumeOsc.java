/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl()
public class StudyVolumeOsc extends Study
{
    @StudyArgSourceLength(name = "short length", defval = 12)
    int shortLen;

    @StudyArgSourceLength(name = "long length", defval = 26)
    int longLen;

    @StudyArgBool(name = "exponential", defval = true)
    boolean exponential;

    @StudyPlot(name = "VolumeOsc")
    Series out;

    public void init()
    {
        final Series volume = volume();
        final Series shortMA = esma(volume, shortLen, exponential);
        final Series longMA = esma(volume, longLen, exponential);
        out = percent(diff(shortMA, longMA), longMA);
    }

}
