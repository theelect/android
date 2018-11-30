package com.tonyecoleelection.domain.entities


class VoterDataModel(val docs: List<PVCDataModel>,
                     val total: Int,
                     val limit: Int,
                     val page: Int,
                     val pages: Int,
                     val total_verified: Int,
                     val total_unverified: Int)