/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "CCI", title = "Commodity Channel Index", shortTitle = "CCI")
@StudyBands({@StudyBand(title = "Upper Band", value = 100, color = "#c0c0c0"),
             @StudyBand(title = "Lower Band", value = -100, color = "#c0c0c0")})
@StudyBandsBackground(color = "#996a15")
public class StudyCCI extends Study
{
    @StudyArgSourceLength(name = "length", defval = 20)
    int length;

    @StudyArgSource(name = "source", defval = BarSet.Source.CLOSE)
    Series source;

    @StudyPlot(name = "CCI")
    @StudyPlotStyle(title = "CCI", color = "#996a15")
    Series out;

    @Override
    public void init()
    {
        out = cci(source, length);
    }
}
