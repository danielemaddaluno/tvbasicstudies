/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "Compare", isPriceStudy = true, title = "Compare", shortTitle = "Compare")
public class StudyCompare extends Study
{
    @StudyArgSourceString(name = "source")
    String source;

    @StudyArgSymbol(name = "symbol")
    String sym;

    @StudyPlot(name = "compare")
    @StudyPlotStyle(title = "Compare", color = "#ff3a00", lineWidth = 2)
    Series out;

    @Override
    public void init()
    {
        out = adopt(overlay(sym, resolution()).sourceByString(source));
    }
}
