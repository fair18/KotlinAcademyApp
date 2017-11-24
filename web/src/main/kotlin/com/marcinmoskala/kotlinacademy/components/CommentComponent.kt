package com.marcinmoskala.kotlinacademy.components

import com.marcinmoskala.kotlinacademy.common.RouteResultProps
import com.marcinmoskala.kotlinacademy.presentation.comment.CommentPresenter
import com.marcinmoskala.kotlinacademy.presentation.comment.CommentView
import com.marcinmoskala.kotlinacademy.views.commentFormView
import com.marcinmoskala.kotlinacademy.views.errorView
import com.marcinmoskala.kotlinacademy.views.loadingView
import kotlinx.html.InputType.text
import react.*
import react.dom.*
import kotlin.properties.Delegates.observable

class CommentComponent : BaseComponent<RouteResultProps<CommentProps>, CommentComponentState>(), CommentView {

    private val presenter by presenter { CommentPresenter(this) }

    override var loading: Boolean by observable(false) { _, _, n ->
        setState { state.loading = n }
    }

    override fun RBuilder.render(): ReactElement? = when {
        state.loading == true -> loadingView()
        state.error != null -> errorView(state.error!!)
        else -> commentFormView(id = props.match.params.id?.toIntOrNull(), onSubmit = presenter::onSendCommentClicked)
    }

    override fun backToNewsAndShowSuccess() {

    }
}

external interface CommentComponentState : BaseState {
    var loading: Boolean?
}

external interface CommentProps : RProps {
    var id: String?
}
