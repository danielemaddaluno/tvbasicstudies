/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;
import java.util.List;

@StudyDecl(studyId = "BB", isPriceStudy = true, title = "Bollinger Bands",
           shortTitle = "BB")
@StudyArea(firstPlotName = "Upper", secondPlotName = "Lower",
           background = @StudyAreaBackground(color = "#138484"))
@StudyOffset(min=-500, val=0, max=500)
public class StudyBollingerBands extends Study
{
    @StudyArgSourceLength(name = "length", defval=20)
    int length;

    @StudyArgSource(name = "source", defval= BarSet.Source.CLOSE)
    Series source;

    @StudyArgDouble(name = "stdDev", minval = 1.0, maxval = 50.0, defval = 2.0)
    double stdDev;

    @StudyPlot(name = "Basis")
    @StudyPlotStyle(title = "Basis", color = "#872323")
    Series outBasis;

    @StudyPlot(name = "Upper")
    @StudyPlotStyle(title = "Upper", color = "#138484")
    Series outUpper;

    @StudyPlot(name = "Lower")
    @StudyPlotStyle(title = "Lower", color = "#138484")
    Series outLower;

    @Override
    public void init()
    {
        List<Series> bb = bb(source, length, stdDev);
        outBasis = bb.get(0);
        outLower = bb.get(1);
        outUpper = bb.get(2);
    }
}
