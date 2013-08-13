/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

import java.util.List;

@StudyDecl(studyId = "DM", title = "Directional Movement", shortTitle = "DMI")
public class StudyDM extends Study
{
    @StudyArgInt(name = "ADX smoothing", minval=1, maxval=50, defval=14)
    int smoothingADX;

    @StudyArgSourceLength(name = "DI Length", defval=14)
    int lengthDI;

    @StudyPlot(name = "ADX")
    @StudyPlotStyle(title = "ADX", color = "#ff006e")
    Series adx;

    @StudyPlot(name = "DI_P")
    @StudyPlotStyle(title = "+DI", color = "#0094ff")
    Series diPlus;

    @StudyPlot(name = "DI_N")
    @StudyPlotStyle(title = "-DI", color = "#ff6a00")
    Series diMinus;

    @Override
    public void init()
    {
        List<Series> dm = dirmov(lengthDI, smoothingADX);
        adx = dm.get(0);
        diPlus = dm.get(1);
        diMinus = dm.get(2);
    }
}
