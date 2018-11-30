package com.tonyecoleelection.data.network.reponses

import com.tonyecoleelection.data.model.PVCDataEntity


class VoterDataPagingResponse(val docs: List<PVCDataEntity>,
                              val total: Int,
                              val limit: Int,
                              val page: Int,
                              val total_verified: Int,
                              val total_unverified: Int,
                              val pages: Int)