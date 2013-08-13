/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

public class SignalCrossOver extends Study
{
    @StudyArgSource(name = "source", defval = BarSet.Source.CLOSE)
    Series source;

    @StudyArgSource(name = "level", defval = BarSet.Source.OPEN)
    Series level;

    @Override
    public void init()
    {
        alertOnCrossOver(source, level);
    }
}
