<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="MessageModalTitle" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content bg-secondary">
            <div class="modal-header">
                    <h5 class="modal-title text-white" id="MessageModalTitle"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body text-white">
                <p id="MessageText"></p>
            </div>
        </div>
    </div>
</div>
