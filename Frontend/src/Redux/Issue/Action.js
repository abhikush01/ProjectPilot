import api from "@/config/api";
import * as actionType from "./ActionType";

export const fetchIssues = (id) => {
  return async (dispatch) => {
    dispatch({ type: actionType.FETCH_ISSUES_REQUEST });
    try {
      const { data } = await api.get(`/api/issues/project/${id}`);
      dispatch({ type: actionType.FETCH_ISSUES_SUCCESS, issues: data });
    } catch (e) {
      console.log(e);
    }
  };
};

export const fetchIssueById = (id) => {
  return async (dispatch) => {
    dispatch({ type: actionType.FETCH_ISSUES_BY_ID_REQUEST });
    try {
      const { data } = await api.get(`/api/issues/${id}`);
      dispatch({ type: actionType.FETCH_ISSUES_BY_ID_SUCCESS, issues: data });
    } catch (e) {
      console.log(e);
    }
  };
};

export const updateIssueStatus = ({ issueId, status }) => {
  return async (dispatch) => {
    dispatch({ type: actionType.UPDATE_ISSUE_STATUS_REQUEST });
    try {
      const { data } = await api.put(`/api/issues/${issueId}/status/${status}`);
      console.log("issueData", data);
      dispatch({ type: actionType.UPDATE_ISSUE_STATUS_SUCCESS, issues: data });
    } catch (e) {
      console.log(e);
    }
  };
};

export const assignUserToIssue = ({ issueId, userId }) => {
  return async (dispatch) => {
    dispatch({ type: actionType.ASSIGNED_ISSUE_TO_USER_REQUEST });
    try {
      const { data } = await api.put(
        `/api/issues/${issueId}/assignee/${userId}`
      );
      dispatch({
        type: actionType.ASSIGNED_ISSUE_TO_USER_SUCCESS,
        issue: data,
      });
    } catch (e) {
      console.log(e);
    }
  };
};

export const deleteIssue = (id) => {
  return async (dispatch) => {
    dispatch({ type: actionType.DELETE_ISSUE_REQUEST });
    try {
      await api.delete(`/api/issues/${id}`);
      dispatch({ type: actionType.DELETE_ISSUE_SUCCESS, issueId: id });
    } catch (e) {
      console.log(e);
    }
  };
};

export const createIssue = (issueData) => {
  return async (dispatch) => {
    dispatch({ type: actionType.CREATE_ISSUE_REQUEST });
    try {
      const { data } = await api.post(`/api/issues`, issueData);
      dispatch({ type: actionType.CREATE_ISSUE_SUCCESS, issue: data });
      console.log("issue created", data);
    } catch (e) {
      console.log(e);
    }
  };
};
