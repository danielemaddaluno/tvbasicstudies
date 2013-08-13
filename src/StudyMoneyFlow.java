/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "MF", title = "Money Flow", shortTitle = "MFl")
public class StudyMoneyFlow extends Study
{
    @StudyArgSourceLength(name = "length", defval=14)
    int length;

    @StudyPlot(name = "MoneyFlow")
    @StudyPlotStyle(title = "MF", color = "#459915")
    Series out;

    @Override
    public void init()
    {
        final Series src = hlc3();
        final Series prev = offset(src, 1);
        final Series upper = sum(mul(replace(src, 0, ge(prev, src)), volume()), length);
        final Series lower = sum(mul(replace(src, 0, ge(src, prev)), volume()), length);
        out = rsi(upper, lower);
    }
}
