/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "CorrelationCoefficient", title = "Correlation Coefficient", shortTitle = "CC")
public class StudyCorrelationCoefficient extends Study
{
    @StudyArgSymbol(name = "symbol", defval = "GOOG")
    String sym;

    @StudyArgSourceString(name = "source", defval = "close")
    String source;

    @StudyArgSourceLength(name = "length", defval = 20)
    int length;

    @StudyPlot(name = "compare")
    @StudyPlotStyle(title = "Correlation", color = "#ff0000", plotStyle = CanvasEx.PlotStyle.AREA)
    Series out;

    @Override
    public void init()
    {
        final StdLib overlay = overlay(sym, resolution());
        final Series baseSeries = sourceByString(source);
        final Series subSeries = overlay.sourceByString(source);
        out = correlation(baseSeries, adopt(subSeries), length);
    }
}