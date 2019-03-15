DELETE FROM PUBLIC.UPDATE_STATUS;
DELETE FROM PUBLIC.TRANSACTION;
DELETE FROM PUBLIC.BLOCK;
DELETE FROM PUBLIC.TWO_FACTOR_AUTH;
DELETE FROM PUBLIC.ACCOUNT;
DELETE FROM PUBLIC.VERSION;
delete from public.transaction_shard_index;
delete from public.block_index;
delete from FTL.INDEXES;
delete from public.option;


INSERT INTO PUBLIC.BLOCK
(DB_ID,         ID,              VERSION,   TIMESTAMP,  PREVIOUS_BLOCK_ID,  TOTAL_AMOUNT, TOTAL_FEE,   PAYLOAD_LENGTH,   PREVIOUS_BLOCK_HASH,                                                   CUMULATIVE_DIFFICULTY,  BASE_TARGET,    NEXT_BLOCK_ID,          HEIGHT,     GENERATION_SIGNATURE,                                                   BLOCK_SIGNATURE,                                                                                                                        PAYLOAD_HASH,                                                           GENERATOR_ID,         TIMEOUT) VALUES
(104830	    ,-468651855371775066 	,3	    ,16758920   , 9108206803338182346 	,0	    ,100000000	        ,1255       ,X'cabec48dd4d9667e562234245d06098f3f51f8dc9881d1959496fd73d7266282'    ,X'026543d9a8161629'    ,9331842	    ,-1868632362992335764	,104595	    ,X'002bc5d6612e35e00e0a8141382eab45c20243d9dad4823348bfe85147b95acf',	X'e920b526c9200ae5e9757049b3b16fcb050b416587b167cb9d5ca0dc71ec970df48c37ce310b6d20b9972951e9844fa817f0ff14399d9e0f82fde807d0957c31'     ,X'37f76b234414e64d33b71db739bd05d2cf3a1f7b344a88009b21c89143a00cd0'	, 9211698109297098287    ,0   ),
(105015	    ,-7242168411665692630	,3	    ,16763026   ,-3475222224033883190   ,0	    ,100000000	        ,1257       ,X'cadbeabccc87c5cf1cf7d2cf7782eb34a58fb2811c79e1d0a3cc60099557f4e0'    ,X'026601a7a1c313ca'    ,7069966	    , 5841487969085496907	,104671 	,X'fbf795ff1d4138f11ea3d38842aa319f8a21589eb46ea8cfc71850f8b55508ef',	X'978b50eb629296b450f5298b61601685cbe965d4995b03707332fdc335a0e708a453bd7969bd9d336fbafcacd89073bf55c3b3395acf6dd0f3204c2a5d4b402e'     ,X'2cba9a6884de01ff23723887e565cbde21a3f5a0a70e276f3633645a97ed14c6'	, 9211698109297098287    ,0   ),
(1641706	,-6746699668324916965	,5	    ,35762774   , 2069655134915376442	,0	    ,200000000	        ,207	    ,X'3ab5313461e4b81c8b7af02d73861235a4e10a91a400b05ca01a3c1fdd83ca7e'	,X'02dfb5187e88edab'	,23058430050	,-3540343645446911906	,1640075	,X'dd7899249f0adf0d7d6f05055f7c6396a4a8a9bd1d189bd5e2eed647f8dfcc0b'	,X'4b415617a8d85f7fcac17d2e9a1628ebabf336285acdfcb8a4c4a7e2ba34fc0f0e54cd88d66aaa5f926bc02b49bc42b5ae52870ba4ac802b8276d1c264bec3f4'	,X'18fa6d968fcc1c7f8e173be45492da816d7251a8401354d25c4f75f27c50ae99'	, 5564664969772495473	,1   ),
(1641707	,-3540343645446911906	,6	    ,35762784   ,-6746699668324916965	,0	    ,0	                ,0	        ,X'1b613faf65e85ea257289156c62ec7d45684759ebceca59e46f8c94961b7a09e'	,X'02dfb518ae37f5ac'	,23058430050	, 2729391131122928659	,1640076	,X'facad4c1e0a7d407e0665393253eaf8e9f1e1e7b26e035687939897eaec9efe3'	,X'f35393c0ff9721c84123075988a278cfdc596e2686772c4e6bd82751ecf06902a942f214c5afb56ea311a8d48dcdd2a44258ee03764c3e25ad1796f7d646185e'	,X'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855'	,-902424482979450876	,4   ),
(1641708	, 2729391131122928659	,6	    ,35762794   ,-3540343645446911906	,0	    ,0	                ,0	        ,X'5e485346362cdece52dada076459abf88a0ae128cac6870e108257a88543f09f'	,X'02dfb518dde6fdad'	,23058430050	, 1842732555539684628	,1640077	,X'1fc63f083c3a49042c43c22a7e4d92aadac95c78d06ed21b8f9f0efd7c23b2a1'	,X'8cb8795d7a320e693e64ea67b47348f2f1099c3e7163311b59135aaff1a78a00542b9d4713928c265666997a4ce63b0c07585ce04464f8dfb2253f21f91bf22e'	,X'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855'	, 4363726829568989435	,3   ),
(1641709	, 1842732555539684628	,4	    ,35762803   , 2729391131122928659	,0	    ,200000000	        ,207	    ,X'130cafd7c5bee025885d0c6b58b2ddaaed71d2fa48423f552eb5828a423cc94b'	,X'02dfb5190d9605ae'	,23058430050	,-5580266015477525080	,1640078	,X'a042a2accbb2600530a4df46db4eba105ac73f4491923fb1c34a6b9dd2619634'	,X'ee4e2ccd12b36ade6318b47246ddcad237a153da36ab9ea2498373a4687c35072f2a9d49925520b588cb16d0e5663f3d10e3adeee97dcbbb4137470e521b347c'	,X'9a8d7e4f2e83dc49351f9c3d72fabc5ecdc75f6eccc2b90f147ff5ec7d5068b2'	, 4363726829568989435	,0   ),
(1641710	,-5580266015477525080	,6	    ,35762813   , 1842732555539684628	,0	    ,0	                ,0	        ,X'149dfdfc7eb39219330d620a14fb0c2f02369abbda562bc4ab068e90c3cf11a4'	,X'02dfb5193d450daf'	,23058430050	, 6438949995368593549	,1640079	,X'20feb26c8c34c22d55de747e3964acb3bc864326736949876d2b0594d15e87dd'	,X'a22f758567f0bd559ce2d821399da4f9ffdc4a694057d8b37045d2a9222be405f4311938e88a0b56418cbadcbea47dadabfc16e58f74e5dcd7a975d95dc17766'	,X'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855'	,-6535098620285495989	,7   ),
(1641711	, 6438949995368593549	,4	    ,35762820   ,-5580266015477525080	,0	    ,200000000	        ,207	    ,X'a81547db9fe98eb224d3cdc120f7305d3b829f162beb3bf719750e0cf48dbe9d'	,X'02dfb5196cf415b0'	,23058430050	, 7551185434952726924	,1640080	,X'5b1bf463f202ec0d4ab42a9634976ed47b77c462d1de25e3fea3e8eaa8add8f6'	,X'992eacb8ac3bcbb7dbdbfcb637318adab190d4843b00da8961fd36ef60718f0f5acca4662cfdcf8447cc511d5e36ab4c321c185382f3577f0106c2bfb9f80ee6'	,X'8bdf98fbc4cfcf0b66dfaa688ce7ef9063f8b1748ee238c23e8209f071cfcee7'	, 6415509874415488619	,0   ),
(1641712	, 7551185434952726924	,6	    ,35762830   , 6438949995368593549	,0	    ,0	                ,0	        ,X'8db872e0e7be5b59fb68ef26d84bfeb9df04f6a5b6f701fd1c88578bfcf48a84'	,X'02dfb5199ca31db1'	,23058430050	, 8306616486060836520	,1640081	,X'1435d4b603b52d04dd0f8228f36dbd6f01e627a59370fa3e6a0f58a75b372621'	,X'5a8acc3cc947b76d42fa78938ed9ece33b91c5ca0bb7a1af6c92ec525e8bb6092babf03aee10bd965123fceb5afad63969e78991d8c6b2a6b4fc79cff8fe150d'	,X'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855'	, 7160808267188566436	,6   ),
(1641713	, 8306616486060836520	,6	    ,35762840   , 7551185434952726924	,0	    ,0	                ,0	        ,X'8cf9752b2533cb6849ad83b275c40f7e61b204ac023f775847a60c2f1a9d3d79'	,X'02dfb519cc5225b2'	,23058430050	,-6206981717632723220	,1640082	,X'5f1382ab768b8b000637d8a59d7415fd8d4b6d4edc00ca0a08aacf9caf8c9a06'	,X'6832f74bd4abe2a4b95755ff9e989133079e5215ae2111e590ea489353ce28078d094db3db077124ac541be9f4f7f09f5a36aac83c8c151dae0f09eb378033e1'	,X'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855'	,-3985647971895643754	,9   ),
(1641714	,-6206981717632723220	,4	    ,35762843   , 8306616486060836520	,0	    ,200000000	        ,207	    ,X'a8460f09af074773186c58688eb29215a81d5b0b10fc9e5fc5275b2f39fd93bb'	,X'02dfb519fc012db3'	,23058430050	,-4166853316012435358	,1640083	,X'df545469ed5a9405e0ff6efcdf468e61564776568c8b227f776f24c47206af46'	,X'3d1c22000eb41599cb12dfbfaa3980353fa84cdf99145d1fcc92886551044a0c0b388c539efa48414c21251e493e468d97a2df12be24e9a33dec4521fdb6c2eb'	,X'550dfe6da8732c1977c7545675f8dc163995aaba5533306b7a1f1b9364190dd3'	, 4749500066832760520	,0   ),
(1641715	,-4166853316012435358	,6	    ,35762855   ,-6206981717632723220	,0	    ,0	                ,0	        ,X'ec562889035fdca9d59d9bdca460992c01c5286278104287a989834eeffcb83e'	,X'02dfb51a2bb035b4'	,23058430050	, 433871417191886464	,1640084	,X'82e59d851fdf0d01ca1ee20df906009cd66885cc63e8314ebde80dc5e38987fa'	,X'202acda4d57f2a24212d265053241a07608de29a6dd8252994cf8be197765d02a585c676aca15e7f43a57d7747173d51435d9f2820da637ca8bc9cd1e536d761'	,X'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855'	, 3883484057046974168	,9   )
  ;

INSERT INTO PUBLIC.TRANSACTION(DB_ID, ID, DEADLINE, RECIPIENT_ID, TRANSACTION_INDEX, AMOUNT, FEE, FULL_HASH, HEIGHT, BLOCK_ID, SIGNATURE, TIMESTAMP, TYPE, SUBTYPE, SENDER_ID, BLOCK_TIMESTAMP, REFERENCED_TRANSACTION_FULL_HASH, PHASED, ATTACHMENT_BYTES, VERSION, HAS_MESSAGE, HAS_ENCRYPTED_MESSAGE, HAS_PUBLIC_KEY_ANNOUNCEMENT, EC_BLOCK_HEIGHT, EC_BLOCK_ID, HAS_ENCRYPTTOSELF_MESSAGE, HAS_PRUNABLE_MESSAGE, HAS_PRUNABLE_ENCRYPTED_MESSAGE, HAS_PRUNABLE_ATTACHMENT) VALUES
  (1481,	-1536976186224925700,	1440,	null,	0,	0,	100000000,	X'fc23d4474d90abeae5dd6d599381a75a2a06e61f91ff2249067a10e6515d202f',	104595,	-468651855371775066,X'61a224ae2d8198bfcee91c83e449d6325a2caa974f6a477ab59d0072b9b7e50793575534ab29c7be7d3dbef46f5e9e206d0bf5801bebf06847a28aa16c6419a1',	16758888,	8,	1,	9211698109297098287,	16758920,	null,	FALSE,	X'01054c494e5558035838360002a427a6d86645d0c32527e50fe292a0b1cf3983ef083f9fc392359e34d90012a65d5bd927c2cd09466433c107e523ff01bc00e414108d01e515f56ddbc054abce83fa4bd30bdf4623928e768536f8e56d9695ebadfbe34b5d1d59aa63545f5238a4817ec09389687df5ec116423b0e572a5ee9c47eaab432b19805a610beecb495595636a14009524caee8f1c73db084f1842bf895440233bff67c8f09674056113efd58da69f8411df3df174438bd2e8280e4eac97d6f89a6d756c1feddccc6d593d59578aab46ad9024b0ba742c547418ea7b2adbed80c8f673cd2cff31fefb6ab068c03232d79dfd83977a05bb0fb286f81ddbc0a9c75e6fce81747223a8fe5e506f9a9d7a7fd08d51b63ba25b4872886857b59607e24e842aa39e9d0d78a3db3ad97b03e64fb135ef55f5f396e29c8a4e146087b853f9a1be0a647201836da32ef5b0bff1a3bc599bff155cbfe8a24ad5ee7ab711bf9de7682876c8b7986025e68c8ee63f63505d3ec21f53a98e9de78f39b69c8438028a0e569f81c9ac7bc7d2dc0ea4f4406a696938fe422bad1076342267ee13d657aa9e68d07aafba6b33fc3e90d72ea5147bc21d223b862c56d989a568a7a2609b272261df3af318f340283490ff4d909768deee8987e363bba10c489d746e4e706daf02b78ba5886f59c204bc2237702d1c2191a6c6b0d3095c9c3d462e4e1cae02f0f53b5e94c2150002b51c553a2e69bc868926235c2fc01ba04b69070324a0c94d9c0d32f65ad4bb475c2b2887800caed2f4023f6510c363a5c4a7da0d8ba7cf85e921990fa7eba87c053ee753157c7541b291483a3f444b0e5d91dcb0f74def9dbe46c910546d0b616ebd9241e7f09aa619cb84b95560307d7e6b07e4fa47c508a621683717485542883203f1f17279b5e93173fa01b19bc707b1ee899bd1118322befed65b6eb28df579d56e61ca6b90abe5408f21544e3e6195ab23876baab07db967de04e815a9395987775acbe57bb7ac8d7366ad62a655bb4598edb4d3d2dce3d326fbeef97b654c686e9abd2c613ea740701a5a4d647e1ebf3bda0fc29fdbb5dfc7dc22842f32e552b0f999076d5f644809ff752224b71fe2f85ad8ac4766d57756d52953bbfb6e6b2134b173bf4995218429371ce3989cd764482396acb05eeaf2e138f38bae9107a9b6db626c6647be5d4a1e6f02f17326700ddeec0b8037671252f0e5c475e06964b6c5a5ff51bc07b494ee84ef5be7d84146f949fe6639409c3fe7550597e45c93ec276721781d9e8677fe4501b583a2b6d96d583c6397c8c5ef14ab6932581d81a8a3518da882fb920dd47c4af25ed755697a7cb181936ae0f21f3c2976f3168202e02fc4b351dcbb7f0c9e5b50a7f1f1d1841dd4de09ca374e3d01fc4fa6cb9271c727a194a2b701ec5e7d882790bb800cc2f86339ad708869ea291105312e302e382000a2c1e47afd4b25035a025091ec3c33ec1992d09e7f3c05875d79e660139220a4',	1,	FALSE,	FALSE,	FALSE,	103874,	1281949812948953897,	FALSE,	FALSE,	FALSE,	FALSE),
  (1539,	-4081443370478530685,	1440,	null,	0,	0,	100000000,	X'830fc103e1cc5bc7a3dd989ad0d7a8c66307a9ef23f05d2a18b661ee0a464088',	104671,	-7242168411665692630,	X'551f99bc4eceaae7c7007ac077ed163f4d95f8acc0119e38b726b5c8b494cf09c5059292de17efbc4ec14848e3944ecd0a5d0ca2591177266e04d426ce25a1c1',	16763004,	8,	0,	9211698109297098287,	16763026,	null,	FALSE,	X'01054c494e555805414d4436340002a427a6d86645d0c32527e50fe292a0b1cf3983ef083f9fc392359e34d90012a65d5bd927c2cd09466433c107e523ff01bc00e414108d01e515f56ddbc054abce83fa4bd30bdf4623928e768536f8e56d9695ebadfbe34b5d1d59aa63545f5238a4817ec09389687df5ec116423b0e572a5ee9c47eaab432b19805a610beecb495595636a14009524caee8f1c73db084f1842bf895440233bff67c8f09674056113efd58da69f8411df3df174438bd2e8280e4eac97d6f89a6d756c1feddccc6d593d59578aab46ad9024b0ba742c547418ea7b2adbed80c8f673cd2cff31fefb6ab068c03232d79dfd83977a05bb0fb286f81ddbc0a9c75e6fce81747223a8fe5e506f9a9d7a7fd08d51b63ba25b4872886857b59607e24e842aa39e9d0d78a3db3ad97b03e64fb135ef55f5f396e29c8a4e146087b853f9a1be0a647201836da32ef5b0bff1a3bc599bff155cbfe8a24ad5ee7ab711bf9de7682876c8b7986025e68c8ee63f63505d3ec21f53a98e9de78f39b69c8438028a0e569f81c9ac7bc7d2dc0ea4f4406a696938fe422bad1076342267ee13d657aa9e68d07aafba6b33fc3e90d72ea5147bc21d223b862c56d989a568a7a2609b272261df3af318f340283490ff4d909768deee8987e363bba10c489d746e4e706daf02b78ba5886f59c204bc2237702d1c2191a6c6b0d3095c9c3d462e4e1cae02f0f53b5e94c2150002b51c553a2e69bc868926235c2fc01ba04b69070324a0c94d9c0d32f65ad4bb475c2b2887800caed2f4023f6510c363a5c4a7da0d8ba7cf85e921990fa7eba87c053ee753157c7541b291483a3f444b0e5d91dcb0f74def9dbe46c910546d0b616ebd9241e7f09aa619cb84b95560307d7e6b07e4fa47c508a621683717485542883203f1f17279b5e93173fa01b19bc707b1ee899bd1118322befed65b6eb28df579d56e61ca6b90abe5408f21544e3e6195ab23876baab07db967de04e815a9395987775acbe57bb7ac8d7366ad62a655bb4598edb4d3d2dce3d326fbeef97b654c686e9abd2c613ea740701a5a4d647e1ebf3bda0fc29fdbb5dfc7dc22842f32e552b0f999076d5f644809ff752224b71fe2f85ad8ac4766d57756d52953bbfb6e6b2134b173bf4995218429371ce3989cd764482396acb05eeaf2e138f38bae9107a9b6db626c6647be5d4a1e6f02f17326700ddeec0b8037671252f0e5c475e06964b6c5a5ff51bc07b494ee84ef5be7d84146f949fe6639409c3fe7550597e45c93ec276721781d9e8677fe4501b583a2b6d96d583c6397c8c5ef14ab6932581d81a8a3518da882fb920dd47c4af25ed755697a7cb181936ae0f21f3c2976f3168202e02fc4b351dcbb7f0c9e5b50a7f1f1d1841dd4de09ca374e3d01fc4fa6cb9271c727a194a2b701ec5e7d882790bb800cc2f86339ad708869ea291105312e302e382000a2c1e47afd4b25035a025091ec3c33ec1992d09e7f3c05875d79e660139220a4',	1,	FALSE,	FALSE,	FALSE,	103950,	3234042379296483074,	FALSE,	FALSE,	FALSE,	FALSE)
;

INSERT INTO PUBLIC.UPDATE_STATUS (transaction_id, updated) VALUES (
  -1536976186224925700, TRUE
);
INSERT INTO PUBLIC.ACCOUNT (ID, BALANCE, UNCONFIRMED_BALANCE, FORGED_BALANCE, ACTIVE_LESSEE_ID, HEIGHT,LATEST)
VALUES (100, 100000000, 100000000, 0, null,	104595, true ),--1
 (200, 250000000, 200000000, 0, null,	104670, true );--2

INSERT INTO PUBLIC.TWO_FACTOR_AUTH (account, secret, confirmed) VALUES
(100, X'a3f312570b65671a7101', true),
 (200, X'f3e0475e0db85a822037', false);
INSERT into PUBLIC.BLOCK_INDEX (shard_id, block_id, block_height) VALUES
(10, 30, 30),
(1, 1, 1),
(2, 2, 2)
;
INSERT into Public.TRANSACTION_SHARD_INDEX(transaction_id, block_id) VALUES
(100, 30),
(101, 1),
(102, 1),
(103, 1)
;

-- set here last value from AplDbVersion
INSERT into PUBLIC.VERSION values(251);
INSERT INTO FTL.INDEXES (schema, table, columns)
                         VALUES('PUBLIC', 'CURRENCY', 'code,name,description');