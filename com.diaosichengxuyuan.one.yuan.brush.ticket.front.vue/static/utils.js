const utils = {
    getRemoteBasePath: function () {
        return "http://localhost:7001";
    },
    getRemoteLoginPath: function () {
        return this.getRemoteBasePath() + "/login";
    },
    getRemoteQueryMemberPath: function () {
        return this.getRemoteBasePath() + "/member/queryMember";
    }
}

export default utils