/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "PriceVolumeTrend", title = "Price Volume Trend", shortTitle = "PVT")
public class StudyPriceVolumeTrend extends Study
{
    @StudyPlot(name = "pvt")
    @StudyPlotStyle(title="PVT", color = "#3a6ca8")
    Series out;

    @Override
    public void init()
    {
        Series src = close();
        Series prev = offset(src, 1);
        out = accum(mul(div(diff(src, prev), prev), volume()));
    }
}
