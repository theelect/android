package com.electionapp.data.network.reponses

import com.electionapp.data.model.PVCDataEntity


class VoterDataPagingResponse(val docs: List<PVCDataEntity>,
                              total: Int,
                              limit: Int,
                              page: Int,
                              pages: Int)