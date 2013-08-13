/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;
import studylib.sdk.studies.PivotPoints;
import java.util.ArrayList;
import java.util.List;


//NOTE: This study has a hardcoded custom properties on the client side (defaults.js)
@StudyDecl(studyId = "PivotPointsHighLow", isPriceStudy = true, isLinkedToSeries = true,
           title = "Pivot Points High Low", shortTitle = "Pivots HL")
public class StudyPivotPointsHL extends Study
{
    @StudyArgSourceLength(name = "length high", defval=10)
    int lengthHigh;

    @StudyArgSourceLength(name = "length low", defval=10)
    int lengthLow;

    public static class Data extends JsonObj
    {
        public List<PivotPointHL> pivots = new ArrayList<>();
    }

    @StudyOutputData
    Data data = new Data();

    @Override
    public void init()
    {
        newSeries(new PivotPoints(lengthHigh, lengthLow, barSet(), data.pivots));
    }
}
