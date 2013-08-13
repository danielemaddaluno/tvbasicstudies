/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "DONCH", isPriceStudy = true, title = "Donchian Channels", shortTitle = "DC")
@StudyArea(firstPlotName = "DonchianUpper", secondPlotName = "DonchianLower",
           background = @StudyAreaBackground(color = "#0094ff"))
public class StudyDonchian extends Study
{
    @StudyArgSourceLength(name = "length", defval=20)
    int length;

    @StudyPlot(name = "DonchianBasis")
    @StudyPlotStyle(title = "Basis", color = "#ff6a00")
    Series basis;

    @StudyPlot(name = "DonchianUpper")
    @StudyPlotStyle(title = "Upper", color = "#0094ff")
    Series upper;

    @StudyPlot(name = "DonchianLower")
    @StudyPlotStyle(title = "Lower", color = "#0094ff")
    Series lower;

    @Override
    public void init()
    {
        lower = lowest(length);
        upper = highest(length);
        basis = avg(lower, upper);
    }
}
