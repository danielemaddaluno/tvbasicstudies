/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

import java.util.List;

@StudyDecl(studyId = "BollingerBandsWidth", title = "Bollinger Bands Width", shortTitle = "BBW")
public class StudyBollingerBandsWidth extends Study
{
    @StudyArgSourceLength(name = "length", defval=20)
    int length;

    @StudyArgSource(name = "source", defval= BarSet.Source.CLOSE)
    Series source;

    @StudyArgInt(name = "stdDev", minval = 1, maxval = 50, defval = 2)
    int stdDev;


    @StudyPlot(name = "BollingerBandsWidth")
    @StudyPlotStyle(title = "Bollinger Bands Width", color = "#138484")
    Series outBBWidth;

    @Override
    public void init()
    {
        List<Series> bb = bb(source, length, stdDev);
        outBBWidth = div(diff(bb.get(2), bb.get(1)), bb.get(0));
    }
}
