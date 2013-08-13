/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;
import studylib.sdk.studies.PivotPointsStandard;
import java.util.ArrayList;
import java.util.List;

//NOTE: This study has a hardcoded custom properties on the client side (defaults.js)
@StudyDecl(studyId = "PivotPointsStandard", isPriceStudy = true, isLinkedToSeries = true,
           title = "Pivot Points Standard", shortTitle = "Pivots")
public class StudyPivotPointsStd extends Study
{
    @StudyArgString(name = "kind", defval = "Traditional",
                    options = {"Traditional", "Fibonacci", "Woodie", "Classic", "DeMark", "Camarilla"},
                    title = "Kind")
    String kind;

    @StudyArgBool(name = "showHistoricalPivots", defval = true, title = "Show historical pivots")
    boolean showHistoricalPivots;

    private static class Data extends JsonObj
    {
        List<PivotPointStd> pivots = new ArrayList<>();
    }

    @StudyOutputData
    Data data = new Data();

    @Override
    public void init()
    {
        final PivotPointsStandard.KindPP kindPP;
        switch (kind.toUpperCase())
        {
            case "TRADITIONAL":
                kindPP = PivotPointsStandard.KindPP.TRADITIONAL;
                break;
            case "FIBONACCI":
                kindPP = PivotPointsStandard.KindPP.FIBONACCI;
                break;
            case "WOODIE":
                kindPP = PivotPointsStandard.KindPP.WOODIE;
                break;
            case "CLASSIC":
                kindPP = PivotPointsStandard.KindPP.CLASSIC;
                break;
            case "DEMARK":
                kindPP = PivotPointsStandard.KindPP.DEMARK;
                break;
            case "CAMARILLA":
                kindPP = PivotPointsStandard.KindPP.CAMARILLA;
                break;
            default:
                kindPP = null;
        }
        assert kindPP != null;

        newSeries(new PivotPointsStandard(barSet(), barBuilder(resolution()),
                data.pivots, showHistoricalPivots, kindPP));
    }
}
