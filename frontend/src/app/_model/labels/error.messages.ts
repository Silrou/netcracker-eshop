export enum ErrorMessages {
  NeedMailConfirmationException = '<h4>Need email confirm</h4>' +
    '<p>To use your account you need click confirmation link in your ' +
    'mail box to confirm the email address</p>',
  NewPasswordSameAsOldException = '<h4>Error: new password invalid</h4>' +
    '<p>New Password cannot be same as previously used password</p>',
  NoUserWithThisEmailException = '<h4>Error wrong email</h4>' +
    '<p>User with this email does not exist</p>',
  OrderCartAmountException = '<h4>Amount problem</h4>' +
    '<p>Some of your product is out of stock ' +
    'please check all product and change amount</p>',
  UserAlreadyExistsException = '<h4>Email Already Registered</h4>' +
    '<p>Your email is already registered.</p>' +
    '<p>If you don\'t know your password please visit the <a href="/forgot-password">' +
    'forgot password</a> page.</p>',
  WrongEmailOrPasswordException = '<h4>Wrong email or password</h4>' +
    '<p>Please check your email and password.</p>',
  ChangeExistMailException = '<h4>This mailbox belongs to another user</h4>' +
    '<p>Please try another mailbox</p>'

}

