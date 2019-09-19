/* global PF */
function handleSubmit(args, dialog) {
    if (args.validationFailed) {
        PF(dialog).jq.effect("shake", {times: 5}, 100);
    } else {
        PF(dialog).hide();
    }
}