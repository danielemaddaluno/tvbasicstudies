/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "RSI", title = "Relative Strength Index", shortTitle = "RSI")
@StudyBands({@StudyBand(title = "Upper Band", value = 70, color = "#c0c0c0"),
             @StudyBand(title = "Lower Band", value = 30, color = "#c0c0c0")})
@StudyBandsBackground(color = "#9915ff")
public class StudyRSI extends Study
{
    @StudyArgSourceLength(name = "length", defval=14)
    int length;

    @StudyArgSource(name = "source", defval=BarSet.Source.CLOSE)
    Series source;

    @StudyPlot(name = "RelativeStrengthIndex")
    @StudyPlotStyle(title = "RSI", color = "#8e1599")
    Series out;

    @Override
    public void init()
    {
        out = rsi(source, length);
    }
}
