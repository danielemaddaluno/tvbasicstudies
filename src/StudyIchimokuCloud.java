/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

@StudyDecl(studyId = "IchimokuCloud", isPriceStudy = true, title = "Ichimoku Cloud", shortTitle = "Ichimoku")
@StudyArea(firstPlotName = "Lead1Line", secondPlotName = "Lead2Line",
           background = @StudyAreaBackground(color = "#773499", transparency = 80))
public class StudyIchimokuCloud extends Study
{
    @StudyArgSourceLength(name = "turning line periods", defval=9)
    int turningLinePeriods;

    @StudyArgSourceLength(name = "standard line periods", defval=26)
    int standardLinePeriods;

    @StudyArgSourceLength(name = "leading span 2 periods", defval=52)
    int leadingSpan2Periods;

    @StudyArgInt(name = "displacement", minval=0, maxval=1000, defval=26)
    int displacement;


    @StudyPlot(name = "TurningLine")
    @StudyPlotStyle(title = "Turning", color = "#459915")
    Series turningLine;

    @StudyPlot(name = "StandardLine")
    @StudyPlotStyle(title = "Standard", color = "#0496ff")
    Series standardLine;

    @StudyPlot(name = "CloseLine")
    @StudyPlotStyle(title = "Close", color = "#991515")
    Series closeLine;

    @StudyPlot(name = "Lead1Line")
    @StudyPlotStyle(title = "Lead1", color = "#773499")
    Series leadingSpan1Line;

    @StudyPlot(name = "Lead2Line")
    @StudyPlotStyle(title = "Lead2", color = "#773499")
    Series leadingSpan2Line;

    @Override
    public void init()
    {
        turningLine = donchian(turningLinePeriods);
        standardLine = donchian(standardLinePeriods);
        closeLine = offset(close(), -displacement);
        leadingSpan1Line = offset(avg(turningLine, standardLine), displacement);
        leadingSpan2Line = offset(donchian(leadingSpan2Periods), displacement);
    }

    Series donchian(int length)
    {
        return avg(lowest(length), highest(length));
    }
}
