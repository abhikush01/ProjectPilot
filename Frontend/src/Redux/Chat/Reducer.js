import * as actionTypes from "./ActionType";

const initialState = {
  messages: [],
  loading: false,
  error: null,
  chat: null,
};

export const chatReducer = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.FETCH_CHAT_MESSAGES_REQUEST:
    case actionTypes.SEND_MESSAGE_REQUEST:
    case actionTypes.FETCH_MESSAGES_REQUEST:
      return { ...state, loading: true, error: null };

    case actionTypes.FETCH_MESSAGES_SUCCESS:
    case actionTypes.FETCH_CHAT_MESSAGES_SUCCESS:
      return {
        ...state,
        loading: false,
        messages: action.messages,
      };
    case actionTypes.SEND_MESSAGE_SUCCESS:
      return {
        ...state,
        loading: false,
        messages: [...state.messages, action.messages],
      };
    case actionTypes.FETCH_CHAT_BY_PROJECT_SUCCESS:
      return {
        ...state,
        loading: false,
        chat: action.chat,
      };
    default:
      return state;
  }
};
