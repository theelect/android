package com.tonyecoleelection.data.network.reponses

import com.tonyecoleelection.data.model.PVCDataEntity


class VoterDataPagingResponse(val docs: List<PVCDataEntity>,
                              total: Int,
                              limit: Int,
                              page: Int,
                              pages: Int)