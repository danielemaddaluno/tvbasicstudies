/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "CMF", title = "Chaikin Money Flow",
           shortTitle = "CMF")
@StudyBand(title = "Zero", value = 0, color = "#606060")
public class StudyChaikinMoneyFlow extends Study
{
    @StudyArgSourceLength(name = "length", defval = 3)
    int length;

    @StudyPlot(name = "ChaikinMoneyFlow")
    @StudyPlotStyle(title = "MF", color = "#459915")
    Series out;

    @Override
    public void init()
    {
        out = div(sum(accdist(true), length),
                  sum(volume(), length));
    }
}
