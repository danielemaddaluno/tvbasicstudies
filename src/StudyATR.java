/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "ATR", title = "Average True Range",
           shortTitle = "ATR")
public class StudyATR extends Study
{
    @StudyArgSourceLength(name = "length", defval=14)
    int length;

    @StudyPlot(name = "ATR")
    @StudyPlotStyle(title = "ATR", color = "#991515")
    Series atr;

    @Override
    public void init()
    {
        atr = atr(length);
    }
}
