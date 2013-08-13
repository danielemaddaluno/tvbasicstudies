/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

import java.util.List;

@StudyDecl(studyId = "BollingerBandsR", title = "Bollinger Bands %R",
           shortTitle = "BB %R")
@StudyBands({@StudyBand(title = "Overbought", value = 1, color = "#606060"),
             @StudyBand(title = "Oversold", value = 0, color = "#606060")})
@StudyBandsBackground(color = "#138484", transparency = 90)
public class StudyBollingerBandsR extends Study
{
    @StudyArgSourceLength(name = "length", defval=18)
    int length;

    @StudyArgSource(name = "source", defval= BarSet.Source.CLOSE)
    Series source;

    @StudyArgInt(name = "stdDev", minval = 1, maxval = 50, defval = 2)
    int stdDev;

    @StudyPlot(name = "BollingerBandsR")
    @StudyPlotStyle(title = "Bollinger Bands %R", color = "#138484")
    Series outBBR;

    @Override
    public void init()
    {
        List<Series> bb = bb(source, length, stdDev);
        Series lower = bb.get(1);
        Series upper = bb.get(2);
        outBBR = div(diff(source, lower), diff(upper, lower));
    }
}
