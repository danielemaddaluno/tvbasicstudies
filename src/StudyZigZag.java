/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;
import studylib.sdk.studies.ZigZag;

import java.util.ArrayList;
import java.util.List;

//NOTE: This study has a hardcoded custom properties on the client side (defaults.js)
@StudyDecl(studyId = "ZigZag", isPriceStudy = true, title = "Zig Zag", shortTitle = "Zig Zag")
public class StudyZigZag extends Study
{
    @StudyArgInt(name = "diviation (%)", minval=1, maxval=100, defval=5)
    int deviation;

    @StudyArgSourceLength(name = "depth", defval=10)
    int depth;

    public static class Data extends JsonObj
    {
        public List<PivotPointHL> zigzag = new ArrayList<>();
    }

    @StudyOutputData
    Data data = new Data();

    @Override
    public void init()
    {
        newSeries(new ZigZag(deviation / 100.0, depth / 2, barSet(), data.zigzag));
    }
}
