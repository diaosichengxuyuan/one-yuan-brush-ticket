const utils = {
    formatDatetime: function (datetime) {
        return new Date(datetime).toLocaleString('chinese', { hour12: false }).replace(/\//g, '-');
    },
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
    getRemoteQueryPassengerPath: function () {
        return this.getRemoteBasePath() + "/passenger/queryPassengers";
    },
    getRemoteInsertTaskPath: function () {
        return this.getRemoteBasePath() + "/task/insertTask";
    }
}

export default utils