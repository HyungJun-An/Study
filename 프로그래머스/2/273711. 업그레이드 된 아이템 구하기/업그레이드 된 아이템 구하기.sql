-- 코드를 작성해주세요
SELECT A.ITEM_ID, ITEM_NAME, RARITY
FROM ITEM_INFO A, ITEM_TREE B
WHERE A.ITEM_ID = B.ITEM_ID AND B.PARENT_ITEM_ID IN(SELECT ITEM_ID
                                                   FROM ITEM_INFO
                                                   WHERE RARITY = 'RARE')
ORDER BY 1 desc