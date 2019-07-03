const utils = {
    getRemoteBasePath: function () {
        return "http://localhost:7001";
    },
    getRemoteLoginPath: function () {
        return this.getRemoteBasePath() + "/login";
    },
    getRemoteQueryMemberPath: function () {
        return this.getRemoteBasePath() + "/member/queryMember";
    },
    getRemotePlacePath: function () {
        return this.getRemoteBasePath() + "/place/queryPlaces";
    },
    getRemoteQueryTicketPath: function () {
        return this.getRemoteBasePath() + "/ticket/queryTickets";
    },
    getRemoteQueryPassengerPath: function(){
        return this.getRemoteBasePath() + "/passenger/queryPassengers";
    }
}

export default utils