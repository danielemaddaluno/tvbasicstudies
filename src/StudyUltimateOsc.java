/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "UltimateOsc", title = "Ultimate Oscillator", shortTitle = "UO")
public class StudyUltimateOsc extends Study
{
    @StudyArgSourceLength(name = "length1", defval=7)
    int length7;
    @StudyArgSourceLength(name = "length2", defval=14)
    int length14;
    @StudyArgSourceLength(name = "length3", defval=28)
    int length28;

    @StudyPlot(name = "UltimateOsc")
    @StudyPlotStyle(title = "Oscillator", color = "red")
    Series out;

    @Override
    public void init()
    {
//        http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:ultimate_oscillator
//        BP = Close - Minimum(Low or Prior Close).
//
//                TR = Maximum(High or Prior Close)  -  Minimum(Low or Prior Close)
//
//        Average7 = (7-period BP Sum) / (7-period TR Sum)
//        Average14 = (14-period BP Sum) / (14-period TR Sum)
//        Average28 = (28-period BP Sum) / (28-period TR Sum)
//
//        UO = 100 x [(4 x Average7)+(2 x Average14)+Average28]/(4+2+1)

        final Series prev = offset(close(), 1);
        final Series high = max(high(), prev);
        final Series low = min(low(), prev);
        final Series bp = diff(close(), low);
        final Series tr = diff(high, low);
        final Series avg7 = average(bp, tr, length7);
        final Series avg14 = average(bp, tr, length14);
        final Series avg28 = average(bp, tr, length28);
        out = percent(sum(mul(avg7, num(4)), mul(avg14, num(2)), avg28), num(7));
    }

    Series average(Series bp, Series tr, int length)
    {
        return div(sum(bp, length), sum(tr, length));
    }
}
