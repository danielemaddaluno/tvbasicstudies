/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

public class SignalCrossOverZero extends Study
{
    @StudyArgSource(name = "source", defval= BarSet.Source.CLOSE)
    Series source;

    @Override
    public void init()
    {
        alertOnCrossOver(source, num(0));
    }
}
