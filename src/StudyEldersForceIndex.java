/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "EFI", title = "Elder's Force Index", shortTitle = "EFI")
@StudyBand(title = "Zero", value = 0, color = "#606060")
public class StudyEldersForceIndex extends Study
{
    @StudyArgSourceLength(name = "length", defval=13)
    int length;

    @StudyPlot(name = "EldersForceIndex")
    @StudyPlotStyle(title = "Elder's Force Index", color = "#d66654")
    Series outEFI;

    @Override
    public void init()
    {
        outEFI = sma(mul(change(close()), volume()), length);
    }
}
