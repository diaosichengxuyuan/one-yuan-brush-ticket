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
    getRemoteLogoutPath: function () {
        return this.getRemoteBasePath() + "/logout";
    },
    getRemoteQueryMemberPath: function () {
        return this.getRemoteBasePath() + "/member/queryMember";
    },
    getRemotePlacePath: function () {
        return this.getRemoteBasePath() + "/place/queryPlaces";
    },
    getRemoteQueryTicketPath: function () {
        return this.getRemoteBasePath() + "/ticket/queryTicketList";
    },
    getRemoteQueryPassengerPath: function () {
        return this.getRemoteBasePath() + "/passenger/queryPassengers";
    },
    getRemoteInsertTaskPath: function () {
        return this.getRemoteBasePath() + "/task/insertTask";
    },
    getRemoteQueryTaskPath: function () {
        return this.getRemoteBasePath() + "/task/queryTaskList";
    },
    getRemoteDeleteTaskPath: function () {
        return this.getRemoteBasePath() + "/task/deleteTaskById";
    },
    getRemoteQueryTaskByIdPath: function () {
        return this.getRemoteBasePath() + "/task/queryTaskById";
    },
    getRemoteStopTaskPath: function () {
        return this.getRemoteBasePath() + "/task/stopTaskById";
    },
    getRemoteStartTaskPath: function () {
        return this.getRemoteBasePath() + "/task/startTaskById";
    },
    getRemoteQueryAcquiredTicket: function () {
        return this.getRemoteBasePath() + "/ticket/queryAcquiredTicketList";
    },
    getRemoteQueryAcquiredTicketByIdPath: function () {
        return this.getRemoteBasePath() + "/ticket/queryAcquiredTicketById";
    },
    getRemoteQueryDataPath: function () {
        return this.getRemoteBasePath() + "/data/queryData";
    },
    isPhoneNumberAvailable: function (phoneNumber) {
        var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
        return reg.test(phoneNumber);
    }
}

export default utils