package sp.phone.presenter.contract.tmp;

import android.content.Intent;
import android.os.Bundle;

import sp.phone.bean.ThreadData;
import sp.phone.bean.ThreadRowInfo;
import sp.phone.forumoperation.ArticleListParam;

/**
 * Created by Justwen on 2017/11/22.
 */

public interface ArticleListContract {

    interface Presenter extends BaseContract.Presenter<View> {

        void loadPage(ArticleListParam param);

        void banThisSB(ThreadRowInfo row);

        void postComment(ArticleListParam param,ThreadRowInfo row);

        void quote(ArticleListParam param,ThreadRowInfo row);
    }

    interface View extends BaseContract.View {

        void setRefreshing(boolean refreshing);

        boolean isRefreshing();

        void hideLoadingView();

        void setData(ThreadData data);

        void startPostActivity(Intent intent);

        void showPostCommentDialog(String prefix, Bundle bundle);

    }

    interface Model extends BaseContract.Model {

    }
}
