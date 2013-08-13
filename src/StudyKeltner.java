/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;

import java.util.List;

@StudyDecl(studyId = "KLTNR", isPriceStudy = true, title = "Keltner Channels", shortTitle = "KC")
@StudyArea(firstPlotName = "KeltnerLower", secondPlotName = "KeltnerUpper",
           background = @StudyAreaBackground(color = "#0094ff"))
public class StudyKeltner extends Study
{
    @StudyArgSourceLength(name = "length", defval=20)
    int length;

    @StudyArgDouble(name = "multiplier", defval=1.0)
    double multiplier;

    @StudyArgSource(name = "source", defval= BarSet.Source.CLOSE)
    Series source;

    @StudyArgBool(name = "exponential", defval=false)
    boolean isExp;

    @StudyArgString(name = "bands style", defval="range", options={"range", "true range"})
    String truerange;

    @StudyPlot(name = "KeltnerUpper")
    @StudyPlotStyle(title = "Upper", color = "#0094ff")
    Series high;

    @StudyPlot(name = "KeltnerCenter")
    @StudyPlotStyle(title = "Basis", color = "#0094ff")
    Series ma;

    @StudyPlot(name = "KeltnerLower")
    @StudyPlotStyle(title = "Lower", color = "#0094ff")
    Series low;

    Series range;
    Series rangema;

    @Override
    public void init()
    {
        ma = esma(source, length, isExp);
        switch(truerange)
        {
            case "range":
                range = diff(high(), low());
                break;
            case "true range":
                range = truerange(true);
                break;
            default:
                assert false;
        }
        rangema = esma(range, length, isExp);
        List<Series> bands = channel(ma, rangema, multiplier);
        low = bands.get(0);
        high = bands.get(1);
    }
}
