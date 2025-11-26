| Version | Correct |BugNoTrim | BugToShort | BugVeryShort | BugWrongMessage | BugNoLengthCheck | BugNoNumber | BugAlwaysTrue | BugWrongHash | custom |
| ------- | ------- |----------| ---------- | ------------ | --------------- | ---------------- | ----------- | ------------- | ------------ | ------ |
| shouldTrimPassword | ✅ | ❌ | ✅ | ❌ | ✅ | ❌ | ✅ | ✅ | ✅ | ✅|
| shouldRejectPasswordThatIsToShort | ✅ | ✅ | ❌ | ❌ | ✅ | ❌ | ✅ | ✅ | ✅ | ✅|
| shouldRejectVeryShortPasswword | ✅ | ✅ | ✅ | ❌ | ✅ | ❌ | ✅ | ✅ | ✅ | ✅|
| shouldThrowToShortMessage | ✅ | ✅ | ✅ | ✅ | ❌ | ❌ | ✅ | ✅ | ✅ | ✅|
| shouldRejectPasswordWithoutNumber | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ❌ | ✅ | ✅ | ✅|
| shouldRejectNotSamePasswords | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ❌ | ✅ | ✅|
| shouldAcceptSamePasswords | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅|
| shouldUseRightHashing | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ❌ | ✅|
| shouldCreateSameHashForSamePassword | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅|
| Covarage | 100% | 100% | 100% | 100% | 100% | 100% | 100% | 100% | 100% | 93% |

