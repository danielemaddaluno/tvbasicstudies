/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

public class SignalCrossOverLevel extends Study
{
    @StudyArgSource(name = "source", defval= BarSet.Source.CLOSE)
    Series source;

    @StudyArgDouble(name = "level", defval = 0)
    double level;

    @Override
    public void init()
    {
        alertOnCrossOver(source, num(level));
    }
}
