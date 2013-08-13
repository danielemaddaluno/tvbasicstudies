/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "WilliamR", title = "Williams %R",
           shortTitle = "%R")
@StudyBands({@StudyBand(title = "Upper Band", value = -20.0, color = "#606060"),
             @StudyBand(title = "Lower Band", value = -80.0, color = "#606060")})
@StudyBandsBackground(color = "#9915ff")
public class StudyWilliamsR extends Study
{
    @StudyArgSourceLength(name = "length", defval=14)
    int length;

    @StudyPlot(name="GetWilliamsRStudy")
    @StudyPlotStyle(title = "", color = "#3a6ca8")
    Series out;

    @Override
    public void init()
    {
        final Series upper = highest(length);
        final Series lower = lowest(length);
        out = percent(diff(close(), upper), diff(upper, lower));
    }
}
