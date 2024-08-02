import * as types from "./ActionType";

const initialState = {
  userSubsription: null,
  loading: false,
  error: null,
};

export const subscriptionReducer = (state = initialState, action) => {
  switch (action.type) {
    case types.GET_USER_SUBSCRIPTION_REQUEST:
    case types.UPGRADE_SUBSCRIPTION_REQUEST:
      return {
        ...state,
        loading: true,
        error: null,
      };
    case types.GET_USER_SUBSCRIPTION_SUCCESS:
      return {
        ...state,
        userSubsription: action.payload,
        loading: false,
        error: null,
      };
    case types.UPGRADE_SUBSCRIPTION_SUCCESS:
      return {
        ...state,
        userSubsription: action.payload,
        loading: false,
        error: null,
      };
    default:
      return state;
  }
};
