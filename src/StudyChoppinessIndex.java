/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "ChoppinessIndex", isPriceStudy = false,
        title = "Choppiness Index", shortTitle = "CHOP")
@StudyOffset(min=-500, val=0, max=500)
@StudyBands({@StudyBand(title = "Upper Band", value = 61.8, color = "#c0c0c0"),
        @StudyBand(title = "Lower Band", value = 38.2, color = "#c0c0c0")})
@StudyBandsBackground(color = "#996a15")
public class StudyChoppinessIndex extends Study
{
    @StudyArgSourceLength(name = "length", defval=14)
    int length;

    @StudyPlot(name = "idx")
    @StudyPlotStyle(title = "CHOP", color = "#3A6CA8")
    Series out;

    @Override
    public void init()
    {
//        100 * LOG10( SUM(ATR(1), x) / ( MaxHi(x) - MinLo(x) ) ) / LOG10(x)
//
//        where x = Choppiness Period
//        LOG10(x) is base-10 LOG of x
//        ATR(1) is the True Range (Period of 1)
//        SUM(ATR(1), x) is the Sum of the True Range over past x bars
//        MaxHi(x) is the highest high over past x bars
        out = mul(num(100.0/Math.log10(length)), log10(div(sum(truerange(), length), diff(highest(length), lowest(length)))));
    }
}
