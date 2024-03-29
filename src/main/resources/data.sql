INSERT INTO book (title, author, publisher, created_at, updated_at) VALUES ('쇼코의 미소', '최진영', '문학동네', NOW(), NOW())
INSERT INTO book (title, author, publisher, created_at, updated_at) VALUES ('어떤 물질의 사랑', '천선란', '아작', NOW(), NOW())
INSERT INTO book (title, author, publisher, created_at, updated_at) VALUES ('우정 도둑', '유지혜', '리더스원', NOW(), NOW())
INSERT INTO book (title, author, publisher, created_at, updated_at) VALUES ('참을 수 없는 존재의 가벼움', '밀란 쿤데라', '민음사', NOW(), NOW())
INSERT INTO book (title, author, publisher, created_at, updated_at) VALUES ('인간 실격', '다자이 오사무', '민음사', NOW(), NOW())
INSERT INTO book (title, author, publisher, created_at, updated_at) VALUES ('데미안', '헤르만 헤세', '민음사', NOW(), NOW())
INSERT INTO book (title, author, publisher, created_at, updated_at) VALUES ('노르웨이의 숲', '무라카미 하루키', '민음사', NOW(), NOW())
INSERT INTO book (title, author, publisher, created_at, updated_at) VALUES ('1984', '조지 오웰', '민음사', NOW(), NOW())

INSERT INTO users (username, nickname,refresh_token_id,password) VALUES ('j','j',null,null)

INSERT INTO Tag (tag) VALUES ('감동')
INSERT INTO Tag (tag) VALUES ('위로')
INSERT INTO Tag (tag) VALUES ('재미')
INSERT INTO Tag (tag) VALUES ('지식')
INSERT INTO Tag (tag) VALUES ('슬픔')
INSERT INTO Tag (tag) VALUES ('행복')
INSERT INTO Tag (tag) VALUES ('응원')
INSERT INTO Tag (tag) VALUES ('사랑')

INSERT INTO quote (content, page, book_id, created_at, updated_at, user_id) VALUES ('시간이 지나고 하나의 관계가 끝날 때마다 나는 누가 떠나는 쪽이고 누가 남겨지는 쪽인지 생각했다. 어떤 경우 나는 떠났고, 어떤 경우 남겨졌지만 정말 소중한 관계가 부서졌을 때는 누가 떠나고 누가 남겨지는 쪽인지 알 수 없었다.', 89, 1, NOW(), NOW(),1)
INSERT INTO quote (content, page, book_id, created_at, updated_at, user_id) VALUES ('이 노인은 얼마나 여러 번 사랑하는 사람들을 잃어버렸을까. 여자는 노인들을 볼 때마다 그런 존경심을 느꼈다. 오래 살아가는 일이란, 사랑하는 사람들을 먼저 보내고 오래도록 남겨지는 일이니까. 그런 일들을 겪곧 다시 일어나 밥을 먹고 홀로 길을 걸어나가야 하는 일이니까.', 238, 1, NOW(), NOW(),1)
INSERT INTO quote (content, page, book_id, created_at, updated_at, user_id) VALUES ('하지만 저는 사막에 가본 적이 없어요.사람이 보는 것을 쓰는 건 아니잖니. 본다고 믿는 것을 쓰지.나는 아버지의 말을 이해하지 못했다. 아버지와 생각이 전혀 다르기 때문일지도 모르겠다. 사람들은 본다고 믿는 것을 쓰는 게 아니라 믿는 것만 본다. 그래서 보는 것만 쓸 수 있다고.', 32, 2, NOW(), NOW(),1)
INSERT INTO quote (content, page, book_id, created_at, updated_at, user_id) VALUES ('사랑과 외로움이라는 단어를 만든 것은 인간이다. 이 땅을 외롭게 만든 것은 오롯이 인간의 짓이라는 걸 상기할 때마다 나는 그저 이 행성을 떠나야만 그 외로움으로부터 벗어날 수 있다는 생각을 했다.', 18, 2, NOW(), NOW(),1)
INSERT INTO quote (content, page, book_id, created_at, updated_at, user_id) VALUES ('아버지는 도로가 없는 우주를 어떻게 달리느냐고 물었다. 정말로 궁금해 물은 것은 아니겠지. 영원히 젊을 것 같던 아버지는 어느새 머리가 전부 새하얗게 셌고 얼굴에 검버섯이 가득해졌다.바다를 항해하는 것과 같아요. 바다에도 도로는 없지만 배가 나아갈 길을 알려주잖아요. 그렇구나. 평생 열심히 땅에 도로를 깔았더니 내 딸은 도로가 없는 길을 가네. 이럴 줄 알았으면 우주에 도로를 깔았어야 했어. 아버지는 너스레를 떨었다. 그러고는 곧 머뭇거리는 내 속마음을 꿰뚫어보고는 말했다.  엄마는 걱정 마라. 이 아빠가 있잖니. 아빠도 이제 엄마 보는 건 익숙해서 아무 문제없거든. 자식은 부모 걱정하는 거 아니다. 그리고 어느 곳이든 네가 나아가는 곳이 길이고, 길은 늘 외롭단다. 적당히 외로움을 길 밖으로 내던지며 나아가야 한다. 외로움이 적재되면 도로도 쉽게 무너지니까. 알겠니?', 32, 2, NOW(), NOW(),1)
INSERT INTO quote (content, page, book_id, created_at, updated_at, user_id) VALUES ('아버지가 설령 보지 않은 것을 보았다고 거짓말했더라도, 내 출발지가 그곳이었음은 변하지 않으니까. 나는 아버지에게 보지 않은 것은 쓸 수 없다고 말했지만 결국 보지 않은 우주를 꿈꿨다. 나는 아무도 가보지 않은 곳을 향해 가고 있고, 긴 주행을 마친 아버지는 현재만이 존재하는 세계에 정착했다. 우리가 갈 수 있도록 그 행성에 텔레포트 설계도를 보냈고, 아주 오랜 시간이 걸린 끝에야 그 행성에서 우리의 숙제를 완수했다. 우리는 그곳에서 지구가 잃은 공기를 다시 찾기 위해 노력하겠지. 내 메시지가 닿는 속도만큼 나는 그 행성으로 나아갈 것이다. 침전되지 않도록 우주 밖으로 외로움을 내던지면서. 그곳에 아직 별이 뜬 사막이 있을까. 당신은 여전히 사막을 꿈꿀까.', 42, 2, NOW(), NOW(),1)
INSERT INTO quote (content, page, book_id, created_at, updated_at, user_id) VALUES ('하지만 가끔 아버지가 말했던 사막의 밤하늘이 딱 그날 하루, 아주 운 좋게 뜨지 않았을까 하는 상상을 했고, 정말로 우주의 누군가가 아버지에게 속삭인 것은 아닐까 오래도록 고민했다. 나를 우주로 보내기 위해서. 어쩌면 지금 우리가 그들에게 보낸 신호가 차원을 돌다가 다시 지구에 닿았던 것은 아닐까. ', 36, 2, NOW(), NOW(),1)
INSERT INTO quote (content, page, book_id, created_at, updated_at, user_id) VALUES ('그들은 서로 사랑했는데도 상대방에게 하나의 지옥을 선사했다.', 36, 4, NOW(), NOW(),1)
INSERT INTO quote (content, page, book_id, created_at, updated_at, user_id) VALUES ('지금 저에게는 행복도 불행도 없습니다. 모든 것은 지나간다는 것. 제가 지금까지 아비규환으로 살아온 소위 인간의 세계에서 단 한 가지 진리처럼 느껴지는 것은 그것뿐입니다. 모든 것은 그저 지나갈 뿐입니다.', 133, 5, NOW(), NOW(),1)
INSERT INTO quote (content, page, book_id, created_at, updated_at, user_id) VALUES ('부끄럼 많은 생애를 보냈습니다.', 13, 5, NOW(), NOW(),1)
INSERT INTO quote (content, page, book_id, created_at, updated_at, user_id) VALUES ('새는 알에서 나오려고 투쟁한다. 알은 세계이다. 태어나려는 자는 하나의 세계를 깨뜨려야 한다. 새는 신에게로 날아간다. 신의 이름은 아브락사스.', 122, 6, NOW(), NOW(),1)
INSERT INTO quote (content, page, book_id, created_at, updated_at, user_id) VALUES ('그러나 잘 되었다. 모든 것이 잘 되었다. 투쟁은 끝이 났다. 그는 자신과의 투쟁에서 승리했다. 그는 빅 브라더를 사랑했다.', 412, 7, NOW(), NOW(),1)
INSERT INTO quote (content, page, book_id, created_at, updated_at, user_id) VALUES ('자신을 동정하지 마. 자신을 동정하는 건 저속한 인간이나 하는 짓이야.', 469, 6, NOW(), NOW(),1)


INSERT INTO quote_tag (quote_id, tag_id) VALUES (2,1)
INSERT INTO quote_tag (quote_id, tag_id) VALUES (2,2)
INSERT INTO quote_tag (quote_id, tag_id) VALUES (3,7)
INSERT INTO quote_tag (quote_id, tag_id) VALUES (3,2)
INSERT INTO quote_tag (quote_id, tag_id) VALUES (3,1)
INSERT INTO quote_tag (quote_id, tag_id) VALUES (4,1)
INSERT INTO quote_tag (quote_id, tag_id) VALUES (4,2)
INSERT INTO quote_tag (quote_id, tag_id) VALUES (4,6)
INSERT INTO quote_tag (quote_id, tag_id) VALUES (4,7)
INSERT INTO quote_tag (quote_id, tag_id) VALUES (5,7)
INSERT INTO quote_tag (quote_id, tag_id) VALUES (8,8)
INSERT INTO quote_tag (quote_id, tag_id) VALUES (9,4)
INSERT INTO quote_tag (quote_id, tag_id) VALUES (10,5)
INSERT INTO quote_tag (quote_id, tag_id) VALUES (11,7)
INSERT INTO quote_tag (quote_id, tag_id) VALUES (12,2)
INSERT INTO quote_tag (quote_id, tag_id) VALUES (12,7)
INSERT INTO quote_tag (quote_id, tag_id) VALUES (13,2)
INSERT INTO quote_tag (quote_id, tag_id) VALUES (13,7)
INSERT INTO quote_tag (quote_id, tag_id) VALUES (13,8)