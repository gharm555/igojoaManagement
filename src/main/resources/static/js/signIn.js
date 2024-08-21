const $adminId = document.querySelector("#adminId");
const $password = document.querySelector("#password");
const $signInBtn = document.querySelector("#signInBtn");

$signInBtn.disabled = true;
$adminId.addEventListener("input", validate);
$password.addEventListener("input", validate);
function validate() {
	const trimmedAdminId = $adminId.value.trim();
	const trimmedPassword = $password.value.trim();

	if (trimmedAdminId.value !== "" && trimmedPassword.value !== "") {
		$signInBtn.disabled = false;
	}

}
