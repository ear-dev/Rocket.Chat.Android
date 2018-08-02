package chat.rocket.android.chatroom.presentation

import android.net.Uri
import chat.rocket.android.chatroom.uimodel.BaseUiModel
import chat.rocket.android.chatroom.uimodel.suggestion.ChatRoomSuggestionUiModel
import chat.rocket.android.chatroom.uimodel.suggestion.CommandSuggestionUiModel
import chat.rocket.android.chatroom.uimodel.suggestion.PeopleSuggestionUiModel
import chat.rocket.android.core.behaviours.LoadingView
import chat.rocket.android.core.behaviours.MessageView
import chat.rocket.core.internal.realtime.socket.model.State
import chat.rocket.core.model.ChatRoom

interface ChatRoomView : LoadingView, MessageView {

    /**
     * Shows the Favorite/Unfavorite chat room icon.
     *
     * @param isFavorite Shows the favorite icon if true, otherwise shows the unfavorite icon.
     */
    fun showFavoriteIcon(isFavorite: Boolean)

    /**
     * Shows the chat room messages.
     *
     * @param dataSet The data set to show.
     */
    fun showMessages(dataSet: List<BaseUiModel<*>>)

    /**
     * Send a message to a chat room.
     *
     * @param text The text to send.
     */
    fun sendMessage(text: String)

    /**
     * Shows the username(s) of the user(s) who is/are typing in the chat room.
     *
     * @param usernameList The list of username to show.
     */
    fun showTypingStatus(usernameList: List<String>)

    /**
     * Hides the typing status view.
     */
    fun hideTypingStatusView()

    /**
     * Perform file selection with the mime type [filter]
     */
    fun showFileSelection(filter: Array<String>?)

    /**
     * Uploads a file to a chat room.
     *
     * @param uri The file URI to send.
     */
    fun uploadFile(uri: Uri)

    /**
     * Shows a invalid file message.
     */
    fun showInvalidFileMessage()

    /**
     * Show activity for sending tokens to another user
     */
    fun showSendTokens()

    /**
     * Show message in the chat room of tokens being sent
     */
    fun displaySentTokens()

    /**
     * Shows a (recent) message sent to a chat room.
     *
     * @param message The (recent) message sent to a chat room.
     */
    fun showNewMessage(message: List<BaseUiModel<*>>)

    /**
     * Dispatch to the recycler views adapter that we should remove a message.
     *
     * @param msgId The id of the message to be removed.
     */
    fun dispatchDeleteMessage(msgId: String)

    /**
     * Dispatch a update to the recycler views adapter about a changed message.
     *
     * @param index The index of the changed message
     */
    fun dispatchUpdateMessage(index: Int, message: List<BaseUiModel<*>>)

    /**
     * Show reply status above the message composer.
     *
     * @param username The username or name of the user to reply/quote to.
     * @param replyMarkdown The markdown of the message reply.
     * @param quotedMessage The message to quote.
     */
    fun showReplyingAction(username: String, replyMarkdown: String, quotedMessage: String)

    /**
     * Copy message to clipboard.
     *
     * @param message The message to copy.
     */
    fun copyToClipboard(message: String)

    /**
     * Show edit status above the message composer.
     */
    fun showEditingAction(roomId: String, messageId: String, text: String)

    /**
     * Disabling the send message button avoids the user tap this button multiple
     * times to send a same message.
     */
    fun disableSendMessageButton()

    /**
     * Enables the send message button.
     */
    fun enableSendMessageButton()

    /**
     * Clears the message composition.
     */
    fun clearMessageComposition()

    fun showInvalidFileSize(fileSize: Int, maxFileSize: Int)

    fun showConnectionState(state: State)

    fun populatePeopleSuggestions(members: List<PeopleSuggestionUiModel>)

    fun populateRoomSuggestions(chatRooms: List<ChatRoomSuggestionUiModel>)
    /**
     * This user has joined the chat callback.
     *
     * @param userCanPost Whether the user can post a message or not.
     */
    fun onJoined(userCanPost: Boolean)

    fun showReactionsPopup(messageId: String)

    /**
     * Show list of commands.
     *
     * @param commands The list of available commands.
     */
    fun populateCommandSuggestions(commands: List<CommandSuggestionUiModel>)

    /**
     * Communicate whether it's a broadcast channel and if current user can post to it.
     */
    fun onRoomUpdated(userCanPost: Boolean, channelIsBroadcast: Boolean, userCanMod: Boolean)

    /**
     * Open a DM with the user in the given [chatRoom] and pass the [permalink] for the message
     * to reply.
     */
    fun openDirectMessage(chatRoom: ChatRoom, permalink: String)
}